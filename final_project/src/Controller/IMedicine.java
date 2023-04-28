package Controller;

import Model.*;

interface IMedicine{
void addMedicine(Medicine item);
void updatePrice(Medicine item);
Medicine getMedicine(String MedName);
String checkExpiry(Medicine item);
}
