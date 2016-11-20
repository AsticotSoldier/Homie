void mode_auto(boolean initial){
  int  col1_1= memory.led1;
  int  col1_2= memory.led2;
  int  col1_3= memory.led3;
  Serial.println(initial);  
  if (!initial){
    col1_1=round(random(0,256));
    col1_2=round(random(0,256));
    col1_3=round(random(0,256));
    initial=1;
  }
   Serial.println(initial);  
  pin_triplet led1;
    led1.r_pin = 11;
    led1.g_pin = 12;
    led1.b_pin = 13;

  pin_triplet led2;
    led2.r_pin = 8;
    led2.g_pin = 10;
    led2.b_pin = 9;
    
  pin_triplet led3;
    led3.r_pin = 3;
    led3.g_pin = 5;
    led3.b_pin = 4;
    
  int  col2_1=round(random(0,256));
  int  col2_2=round(random(0,256));
  int  col2_3=round(random(0,256));
    Serial.print(col1_1);   Serial.print("vers"); Serial.println(col2_1);  
  transition_color(col1_1, col2_1,col1_2, col2_2,col1_3, col2_3, 100,round(random(3,61))*1000,led1,led2,led3);
  memory.led1=col2_1;
  memory.led2=col2_2;
  memory.led3=col2_3;
}




