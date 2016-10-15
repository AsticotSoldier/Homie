void write_color_triplet(rgb triplet,pin_triplet nb_pin){
  analogWrite(nb_pin.r_pin, triplet.r);
  analogWrite(nb_pin.g_pin, triplet.g);
  analogWrite(nb_pin.b_pin, triplet.b);
}

