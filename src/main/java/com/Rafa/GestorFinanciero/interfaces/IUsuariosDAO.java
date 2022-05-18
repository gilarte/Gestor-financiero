package com.Rafa.GestorFinanciero.interfaces;

import java.util.List;

import com.Rafa.GestorFinanciero.model.Usuario;

public interface IUsuariosDAO {

	public boolean insert(Usuario u);
	public boolean update(Usuario u);
	public List<Usuario> getAll();
	
	
}
