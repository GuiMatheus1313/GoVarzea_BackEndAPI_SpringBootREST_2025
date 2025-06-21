package com.govarzeasocial.social.service;

import com.govarzeasocial.social.dto.TimeJogadoresResponseDTO;
import com.govarzeasocial.social.model.Jogador;
import com.govarzeasocial.social.model.Time;
import com.govarzeasocial.social.model.TimeJogadores;
import com.govarzeasocial.social.model.TimeJogadoresPK;
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

    public Time insert(Time time) {
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

    @Transactional
    public void adicionarJogadorAoTime(Long timeId, String jogadorCpf) {
        Time time = findById(timeId);
        Jogador jogador = jogadorService.findById(jogadorCpf);
        //
        TimeJogadoresPK pkTimeJogadores = new TimeJogadoresPK(time.getIdTime(), jogador.getCpf());
        timeJogadoresRepo.save(new TimeJogadores(pkTimeJogadores));
        //
    }

    @Transactional
    public void removerJogadorDoTime(Long timeId, String jogadorCpf) {
        TimeJogadoresPK pkTimeJogadores = new TimeJogadoresPK(timeId, jogadorCpf);

        if (!timeJogadoresRepo.existsById(pkTimeJogadores)) {
            throw new RuntimeException("Associação Time x Jogador não encontrada para remoção.");
        }

        timeJogadoresRepo.deleteById(pkTimeJogadores);
    }

    public List<TimeJogadoresResponseDTO> listarTimesComJogadores() {
        List<Time> times = timeRepo.findAll();

        return times.stream().map(time -> {
            List<TimeJogadores> relacoes = timeJogadoresRepo.findByTimeJogadoresPK_TimeIdPK(time.getIdTime());

            List<Jogador> jogadores = relacoes.stream()
                    .map(rel -> jogadorService.findById(rel.getTimeJogadoresPK().getJogadorCPFPK()))
                    .collect(Collectors.toList());

            TimeJogadoresResponseDTO dto = new TimeJogadoresResponseDTO();
            dto.setIdTime(time.getIdTime());
            dto.setNome(time.getNome());
            dto.setLocalizacao(time.getLocalizacao());
            dto.setFundacao(time.getFundacao());
            dto.setFkDirigente(time.getFkDirigente());
            dto.setJogadores(jogadores);

            return dto;
        }).collect(Collectors.toList());
    }
}
