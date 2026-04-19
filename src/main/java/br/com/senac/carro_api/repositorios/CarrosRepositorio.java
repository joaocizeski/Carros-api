package br.com.senac.carro_api.repositorios;

import br.com.senac.carro_api.entidades.Carros;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrosRepositorio extends JpaRepository<Carros, Long> {
}
