import { CuentaCorriente } from "./cuenta-corriente";
import { Patente } from "./patente";
export interface Driver {
    id: number;
    phone: string;
    licensePlates: Patente[];
    wallet: CuentaCorriente;
}
