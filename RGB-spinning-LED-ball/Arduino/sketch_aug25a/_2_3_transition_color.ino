void transition_color( int color_dbt, int color_fin, float nb_pas,int time_transition,pin_triplet nb_pin){
  float ecart=color_fin-color_dbt;
  int activ_short_way=0;
  
  if (abs(ecart>128)){
    ecart=-(255-ecart);
    activ_short_way=1;
  }
  double inc_ecart=ecart/nb_pas;
  int current_color=0;
  
  for(int i=0; i<nb_pas;i++){
    current_color=color_dbt+inc_ecart*i+255*activ_short_way;
    current_color=( current_color % 256);
    rgb rgb_triplet=color_map(round(current_color));
    write_color_triplet(rgb_triplet, nb_pin);
    delay(round(time_transition/nb_pas));  
  }
}
