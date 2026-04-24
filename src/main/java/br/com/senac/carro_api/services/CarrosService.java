package br.com.senac.carro_api.services;

import br.com.senac.carro_api.dtos.CarrosFiltroDto;
import br.com.senac.carro_api.dtos.CarrosRequestDto;
import br.com.senac.carro_api.entidades.Carros;
import br.com.senac.carro_api.repositorios.CarrosRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarrosService {

    private CarrosRepositorio carrosRepositorio;

    public CarrosService(CarrosRepositorio carrosRepositorio) {
        this.carrosRepositorio = carrosRepositorio;
    }

    public List<Carros> listar(CarrosFiltroDto filtro){
        if (filtro.getModelo()!=null){
            return carrosRepositorio
                    .findByModelo(filtro.getModelo());
        }

        return carrosRepositorio.findAll();
    }

    public Carros criar(CarrosRequestDto carro){
        Carros carroPersist = this.carrosRequestDtoParaCarros(carro);

        return carrosRepositorio.save(carroPersist);
    }

    public Carros atualizar(Long id, CarrosRequestDto carrosRequestDto){
        if (carrosRepositorio.existsById(id)) {
            CarrosRequestDto carros;
            Carros carrosPersist = this.carrosRequestDtoParaCarros(carrosRequestDto);

            carrosPersist.setId(id);

            return carrosRepositorio.save(carrosPersist);
        }
        throw new RuntimeException("Client não encontrado");
    }

    public void deletar(Long id) {
        if (carrosRepositorio.existsById(id)){
            carrosRepositorio.deleteById(id);
        }
        throw new RuntimeException("Carro não encontrado");
    }

// ----------------------------------------------------------------------------------------------------------

    public Carros listarById(Long id){
        if (carrosRepositorio.existsById(id)){
            return carrosRepositorio.findById(id).get();
        }
        throw new RuntimeException("Carro não Existe");
    }

    private Carros carrosRequestDtoParaCarros(CarrosRequestDto entrada){
        Carros saida = new Carros();
        saida.setModelo(entrada.getModelo());
        saida.setMarca(entrada.getMarca());
        saida.setAno(entrada.getAno());
        saida.setPlaca(entrada.getPlaca());

        return saida;
    }


}
