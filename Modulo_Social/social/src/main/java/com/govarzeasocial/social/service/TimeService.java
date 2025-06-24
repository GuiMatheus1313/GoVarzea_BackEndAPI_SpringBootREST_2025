package com.govarzeasocial.social.service;

import com.govarzeasocial.social.dto.TimeJogadoresResponseDTO;
import com.govarzeasocial.social.model.*;
import com.govarzeasocial.social.repository.TimeJogadoresRepo;
import com.govarzeasocial.social.repository.TimeRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimeService {

    @Autowired
    private TimeRepo timeRepo;

    @Autowired
    private DirigenteService dirigenteService;

    @Autowired
    private TimeJogadoresRepo timeJogadoresRepo;

    @Autowired
    private JogadorService jogadorService;

    public List<Time> findAll() {
        return timeRepo.findAll();
    }

    public Time findById(Long id) {
        return timeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));
    }

    public List<Time> findByNome(String nome){
        return timeRepo.findAllByNomeContainingIgnoreCase(nome);
    }

    public Time insert(Time time) {
        Dirigente dirigentedb = dirigenteService.findByCpf(time.getDirigente().getCpf());
        time.setDirigente(dirigentedb);
        return timeRepo.save(time);
    }

    public Time edit(Long id, Time updatedTime) {
        Time time = timeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Time não encontrado com ID: " + id));

        time.setNome(updatedTime.getNome());
        time.setLocalizacao(updatedTime.getLocalizacao());
        time.setFundacao(updatedTime.getFundacao());

        return timeRepo.save(time);
    }

    public String delete(Long id) {
        timeRepo.deleteById(id);
        return "Time deletado com sucesso.";
    }

    //PARTE TIMEJOGADORES

    @Transactional
    public void adicionarJogadorAoTime(Long timeId, String jogadorCpf) {
        Time time = findById(timeId);
        Jogador jogador = jogadorService.findByCpf(jogadorCpf);

        TimeJogadores relacao = new TimeJogadores(time, jogador);

        timeJogadoresRepo.save(relacao);


    }

    @Transactional
    public void removerJogadorDoTime(Long timeId, String jogadorCpf) {
        Jogador jogador = jogadorService.findByCpf(jogadorCpf);
        TimeJogadoresPK pk = new TimeJogadoresPK(timeId, jogador.getJogadorID());

        if (!timeJogadoresRepo.existsById(pk)) {
            throw new RuntimeException("Relação Time x Jogador não encontrada.");
        }

        timeJogadoresRepo.deleteById(pk);
    }

    public List<TimeJogadoresResponseDTO> listarTimesComJogadores() {
        List<Time> times = timeRepo.findAll();

        return times.stream().map(time -> {
            List<TimeJogadores> relacoes = timeJogadoresRepo.findByTime_IdTime(time.getIdTime());

            List<Jogador> jogadores = relacoes.stream()
                    .map(TimeJogadores::getJogador)
                    .collect(Collectors.toList());

            TimeJogadoresResponseDTO dto = new TimeJogadoresResponseDTO();
            dto.setIdTime(time.getIdTime());
            dto.setNome(time.getNome());
            dto.setLocalizacao(time.getLocalizacao());
            dto.setFundacao(time.getFundacao());
            dto.setFkDirigente(time.getDirigente());
            dto.setJogadores(jogadores);

            return dto;
        }).collect(Collectors.toList());

    }
}
