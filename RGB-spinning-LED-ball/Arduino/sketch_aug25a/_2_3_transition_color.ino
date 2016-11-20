void transition_color( int color_dbt1, int color_fin1,int color_dbt2, int color_fin2,int color_dbt3, int color_fin3, float nb_pas,int time_transition,pin_triplet num_pin_led1,pin_triplet num_pin_led2,pin_triplet num_pin_led3){
  float ecart1=color_fin1-color_dbt1;
  float ecart2=color_fin2-color_dbt2;
  float ecart3=color_fin3-color_dbt3;
  
  int activ_short_way1=0;
  int activ_short_way2=0;
  int activ_short_way3=0;
  
  if (abs(ecart1>128)){
    ecart1=-(255-ecart1);
    activ_short_way1=1;
  }
    if (abs(ecart2>128)){
    ecart2=-(255-ecart2);
    activ_short_way2=1;
  }
     if (abs(ecart3>128)){
    ecart3=-(255-ecart3);
    activ_short_way3=1;
  }
  
double inc_ecart1=ecart1/nb_pas;
double inc_ecart2=ecart2/nb_pas;
double inc_ecart3=ecart3/nb_pas;

int current_color1=0;
int current_color2=0;
int current_color3=0;
  
  for(int i=0; i<nb_pas;i++){
    current_color1=color_dbt1+inc_ecart1*i+255*activ_short_way1;
    current_color1=( current_color1 % 256);
    rgb rgb_triplet1=color_map(round(current_color1));
 Serial.println(current_color1);
    current_color2=color_dbt2+inc_ecart2*i+255*activ_short_way2;
    current_color2=( current_color2 % 256);
    rgb rgb_triplet2=color_map(round(current_color2));

    current_color3=color_dbt3+inc_ecart3*i+255*activ_short_way3;
    current_color3=( current_color3 % 256);
    rgb rgb_triplet3=color_map(round(current_color3));
    
    write_color_triplet(rgb_triplet1, num_pin_led1);
    write_color_triplet(rgb_triplet2, num_pin_led2);
    write_color_triplet(rgb_triplet3, num_pin_led3);
    
    delay(round(time_transition/nb_pas));
    yield();
  }
}
