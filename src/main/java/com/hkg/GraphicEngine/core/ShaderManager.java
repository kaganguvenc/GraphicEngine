package com.hkg.GraphicEngine.core;

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class ShaderManager {

    private final int programID;
    private int vertexShaderID, fragmentShaderID;

    public ShaderManager() throws Exception {
        this.programID = GL20.glCreateProgram();
        if (programID == 0) {
            throw new Exception("Could not create program!");
        }
    }

    public void createVertexShader(String shaderCode) throws Exception {
        vertexShaderID = createShader(shaderCode, GL20.GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String shaderCode) throws Exception {
        fragmentShaderID = createShader(shaderCode, GL20.GL_FRAGMENT_SHADER);
    }

    private int createShader(String shaderCode, int shaderType) throws Exception {
        int shaderID = GL20.glCreateShader(shaderType);
        if (shaderID == 0) {
            throw new Exception("Error creating shader type: " + shaderType);
        }
        GL20.glShaderSource(shaderID, shaderCode);
        GL20.glCompileShader(shaderID);

        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == 0) {
            throw new Exception("Error compiling shader type: " + shaderType + "\n Info: " + GL20.glGetShaderInfoLog(shaderID, 1024));
        }
        GL20.glAttachShader(programID, shaderID);
        return shaderID;
    }

    public void link() throws Exception {
        GL20.glLinkProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_LINK_STATUS) == 0) {
            throw new Exception("Error linking shader " + "\n Info: " + GL20.glGetProgramInfoLog(programID, 1024));
        }
        if (vertexShaderID != 0) {
            GL20.glDetachShader(programID, vertexShaderID);
        }
        if (fragmentShaderID != 0) {
            GL20.glDetachShader(programID, fragmentShaderID);
        }
        GL20.glValidateProgram(programID);
        if (GL20.glGetProgrami(programID, GL20.GL_VALIDATE_STATUS) == 0) {
            throw new Exception("Unable to validate shader code " + "\n Info: " + GL20.glGetProgramInfoLog(programID, 1024));
        }
    }

    public void bind() {
        GL20.glUseProgram(programID);
    }

    public void unbind() {
        GL20.glUseProgram(0);
    }

    public void cleanup() {
        unbind();
        if (programID != 0) {
            GL20.glDeleteProgram(programID);
        }
    }

}
