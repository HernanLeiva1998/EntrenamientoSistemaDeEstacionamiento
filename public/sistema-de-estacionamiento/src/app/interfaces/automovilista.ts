import { CuentaCorriente } from "./cuenta-corriente";
import { Patente } from "./patente";
export interface Automovilista {
    id: number;
    telefono: string;
    patentes: Patente[];
    cuentaCorriente: CuentaCorriente;
}
