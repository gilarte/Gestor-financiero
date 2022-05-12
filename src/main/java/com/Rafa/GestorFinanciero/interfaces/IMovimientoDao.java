package com.Rafa.GestorFinanciero.interfaces;

import java.util.List;

import com.Rafa.GestorFinanciero.model.Movimientos;

public interface IMovimientoDao {

	public boolean insertar(Movimientos m);
	public boolean delete(int id);
	public boolean update(Movimientos m);
	public List<Movimientos> getAll();

}
