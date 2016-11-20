void write_color_triplet(rgb triplet,pin_triplet nb_pin){
  if (!patternOn) {
    analogWrite(nb_pin.r_pin, triplet.r);
    analogWrite(nb_pin.g_pin, triplet.g);
    analogWrite(nb_pin.b_pin, triplet.b);
  }
  else{
    Connected_Pattern();
  }
}


void Connected_Pattern(){
analogWrite(2,0);
analogWrite(3,0);
analogWrite(5,0);
analogWrite(8,0);
analogWrite(9,0);
analogWrite(10,0);
analogWrite(11,0);
analogWrite(12,0);
delay(1000);
analogWrite(13,255);
analogWrite(10,255);
analogWrite(5,255);
delay(250);
analogWrite(13,255);
analogWrite(10,255);
analogWrite(5,255);
delay(1000);
analogWrite(13,0);
analogWrite(10,0);
analogWrite(5,0);
patternOn=0;
}
