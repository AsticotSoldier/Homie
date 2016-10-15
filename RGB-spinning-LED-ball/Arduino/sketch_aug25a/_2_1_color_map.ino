rgb color_map(int color_index) {

int r_out=0;
int g_out=0;
int b_out=0;

if (color_index >=0 && color_index<42){
r_out=255;
g_out=map(color_index, 0, 42, 0, 255); 
b_out= 0;  
}

else if (color_index >=42 && color_index<85){
r_out=map(color_index, 42, 85, 255, 0);
g_out=255;
b_out=0;    
}

else if (color_index >=85 && color_index<127){
r_out=0;
g_out=255;
b_out=map(color_index, 85, 127, 0, 255);   
}

else if (color_index >=127 && color_index<170){
r_out=0;
g_out=map(color_index, 127, 170, 255, 0);
b_out=255;    
}

else if (color_index >=170 && color_index<212){
r_out=map(color_index, 170, 212, 0, 255); ; 
g_out=0; 
b_out=255;   
}

else if (color_index >=170 && color_index<=255){
r_out=255; 
g_out=0; 
b_out=map(color_index, 212, 255, 255, 0);   
}

rgb mapping;
mapping.r=round(r_out);
mapping.g=round(g_out);
mapping.b=round(b_out);

return mapping; 
}
