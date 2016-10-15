void mode_auto(boolean initialize,int nb_led){
 //LED part
  switch (nb_led){
  case 1:
    auto_1(initialize);
  break;
  case 2:
    auto_2(initialize);
  break;
  case 3:
    auto_3(initialize);
  break ;
  }
  //motor part
}

//LED functions
void auto_1(boolean initialize){
  int  col1_1= memory.led1;
  if (!initialize){
    col1_1=round(random(0,256));
  }
  pin_triplet led1;
    led1.r_pin = 11;
    led1.g_pin = 12;
    led1.b_pin = 13;
  int  col2_1=round(random(0,256));
  transition_color(col1_1, col2_1, 100,round(random(5,31))*1000,led1);
  memory.led1=col2_1;
}

void auto_2(boolean initialize){
  int  col1_2= memory.led2;
  if (!initialize){
    col1_2=round(random(0,256));
  }
  pin_triplet led2;
    led2.r_pin = 8;
    led2.g_pin = 10;
    led2.b_pin = 9;
  int  col2_2=round(random(0,256));
  transition_color(col1_2, col2_2, 100,round(random(5,31))*1000,led2);
  memory.led2=col2_2;
}

void auto_3(boolean initialize){
  int  col1_3= memory.led3;
  if (!initialize){
    col1_3=round(random(0,256));
  }
  pin_triplet led3;
    led3.r_pin = 3;
    led3.g_pin = 5;
    led3.b_pin = 4;
  int  col2_3=round(random(0,256));
  transition_color(col1_3, col2_3, 100,round(random(5,31))*1000,led3);
  memory.led3=col2_3;
}

