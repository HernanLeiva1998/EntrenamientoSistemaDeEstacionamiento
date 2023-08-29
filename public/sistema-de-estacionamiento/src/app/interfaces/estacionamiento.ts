import { Driver } from "./driver";
import { Patente } from "./patente";

export interface Estacionamiento {
    id: number;
    parkingStart: Date;
    parkingEnd: Date;
    totalCost: number;
    isActive: boolean;
    driver: Driver;
    licensePlate: Patente;
}
