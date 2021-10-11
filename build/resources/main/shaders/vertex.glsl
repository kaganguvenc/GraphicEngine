#version 400 core

in vec3 position;

out vec4 colour;

void main(){
    gl_Position = vec4(position,1.0f);
    colour = vec4(position.x+0.25f,0.17f,position.y+.25f,1.0f);

}
