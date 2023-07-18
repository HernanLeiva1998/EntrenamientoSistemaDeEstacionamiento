import { Automovilista } from "./automovilista";
import { Patente } from "./patente";

export interface Estacionamiento {
    id: number;
    inicioDeEstacionamiento: Date;
    finDeEstacionamiento: Date;
    monto: number;
    estaActiva: boolean;
    automovilista: Automovilista;
    patente: Patente;
}
