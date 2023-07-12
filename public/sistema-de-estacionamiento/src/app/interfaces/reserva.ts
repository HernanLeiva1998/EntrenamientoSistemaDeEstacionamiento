import { Automovilista } from "./automovilista";
import { Patente } from "./patente";

export interface Reserva {
    id: number;
    inicioDeReserva: Date;
    finDeReserva: Date;
    monto: number;
    estaActiva: boolean;
    automovilista: Automovilista;
    patente: Patente;
}
