/* 25.08.2016 -- RGB spinning LED ball
 Pierre-Yves Bloqueau
----Etat d'avancement: x- fait o- à faire
 o-lien entre appli android et arduino
  o- distinction entre mode auto et manuel
    o-codage mode auto
      x- Look up-table: 1int-->3int rgb 
      o-fonction de transition mot
      x-fonction de transition color
    o-codage mode manuel moteur
    o-codage mode manuel led
    o-codage mode manuel full
    o-codage enregistrement mode  
    
    1:declaration
    2:couleur LED
    3:moteur
    4:BLE
    5:modes généraux*/

#include "_1_declaration.h"
#include <Scheduler.h>
// BLE & SPI
#include <SPI.h>
#include <BLEPeripheral.h>

boolean initial=0;

index_led memory;

void setup() {
  Serial.begin(115200);
  while (!Serial);
Serial.println("prog start");
  randomSeed(analogRead(0));
  Scheduler.startLoop(loop2);
  Scheduler.startLoop(loop3);
  Scheduler.startLoop(loop4);
}

void loop() {
  
  int switch_mode=1;

  switch (switch_mode) {
    case 1:
     mode_auto(initial,1);

      break;
    case 2:
     //to do
      break;
    case 3:
      //to do
    break;
    case 4:
      //to do
    break;
    Serial.println("loop1");
    yield();
  }

initial=1;
}

void loop2(){
mode_auto(initial,2);
Serial.println("loop2");
yield();
}

void loop3(){
mode_auto(initial,3);
Serial.println("loop3");
yield();
}
