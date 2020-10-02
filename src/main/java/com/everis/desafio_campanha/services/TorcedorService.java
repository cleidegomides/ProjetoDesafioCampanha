package com.everis.desafio_campanha.services;


import com.everis.desafio_campanha.dto.TorcedorDto;
import com.everis.desafio_campanha.entites.Torcedor;
import com.everis.desafio_campanha.exceptions.EmailInvalidoException;
import com.everis.desafio_campanha.exceptions.SenhaNaoConfereException;
import com.everis.desafio_campanha.exceptions.TorcedorNotFoundException;
import com.everis.desafio_campanha.mappers.MapperTorcedorTorcedorDto;
import com.everis.desafio_campanha.repositorys.TorcedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Service
public class TorcedorService {

    @Autowired
    private TorcedorRepository torcedorRepository;

    @Autowired
    private MapperTorcedorTorcedorDto mapperTorcedorTorcedorDto;

    public void criarTorcedor(TorcedorDto torcedorDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Torcedor torcedorBD = torcedorRepository.findByEmail(torcedorDto.getEmail());

        if (torcedorBD == null){
            Torcedor torcedor = mapperTorcedorTorcedorDto.toEntity(torcedorDto);

            if (!torcedorDto.getSenha().equals(torcedorDto.getConfirmarSenha())){
                throw new SenhaNaoConfereException("Senha não confere!");
            }


            torcedorRepository.save(torcedor);
        } else{
            throw new EmailInvalidoException("Email já cadastrado!");
        }
    }

    public TorcedorDto buscarTorcedor(Long id) {

        Torcedor torcedor = validarTorcedor(id);

        TorcedorDto torcedorDto = mapperTorcedorTorcedorDto.toDto(torcedor);


        return torcedorDto;
    }

    private Torcedor validarTorcedor(Long id){
        Optional<Torcedor> optionalTorcedor = torcedorRepository.findById(id);

        if (!optionalTorcedor.isPresent()){
            throw new TorcedorNotFoundException("Torcedor não encontrado!");
        }

        Torcedor torcedor = optionalTorcedor.get();

        return torcedor;

    }

    public void deletarTorcedor(Long id) {
        Torcedor torcedor = validarTorcedor(id);

        torcedorRepository.delete(torcedor);
    }

    public List<Torcedor> listarTorcedores() {
        List<Torcedor> torcedores = torcedorRepository.findAll();
        return torcedores;
    }
}
