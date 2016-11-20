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
boolean patternOn=0;
index_led memory;

void setup() {
  Serial.begin(250000);
  while (!Serial);
  Serial.println("prog start");
  randomSeed(analogRead(0));
  BLE_init();
  Scheduler.startLoop(loop2);
}

void loop() {
  mode_auto(initial);
  yield();
}

