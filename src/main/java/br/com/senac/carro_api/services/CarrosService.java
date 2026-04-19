package br.com.senac.carro_api.services;

import br.com.senac.carro_api.dtos.CarrosRequestDto;
import br.com.senac.carro_api.entidades.Carros;
import br.com.senac.carro_api.repositorios.CarrosRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrosService {

    private CarrosRepositorio carrosRepositorio;

    public CarrosService(CarrosRepositorio clientesRepositorio) {
        this.carrosRepositorio = clientesRepositorio;
    }

    public List<Carros> listar(){
        return carrosRepositorio.findAll();
    }

    public Carros criar(CarrosRequestDto cliente){
        Carros clientePersist = this.clientesRequestDtoParaClientes(cliente);

        return carrosRepositorio.save(clientePersist);
    }

    public Carros atualizar(Long id, CarrosRequestDto clientesRequestDto){
        if (carrosRepositorio.existsById(id)) {
            CarrosRequestDto cliente;
            Carros clientesPersist = this.clientesRequestDtoParaClientes(clientesRequestDto);

            return carrosRepositorio.save(clientesPersist);
        }
        throw new RuntimeException("Cliente não encontrado");
    }

    public void deletar(Long id) {
        if (carrosRepositorio.existsById(id)){
            carrosRepositorio.deleteById(id);
        }
        throw new RuntimeException("Cliente não encontrado");
    }

// ----------------------------------------------------------------------------------------------------------

    private Carros clientesRequestDtoParaClientes(CarrosRequestDto entrada){
        Carros saida = new Carros();
        saida.setModelo(entrada.getModelo());
        saida.setMarca(entrada.getMarca());
        saida.setAno(entrada.getAno());
        saida.setPlaca(entrada.getPlaca());

        return saida;
    }


}
