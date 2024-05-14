package desafio.votacao.service.impl;

import desafio.votacao.dto.PautaDTO;
import desafio.votacao.dto.response.AssociadoDTO;
import desafio.votacao.dto.response.ContabilizaVotosDto;
import desafio.votacao.dto.response.ResponseSessaoVotacaoDTO;
import desafio.votacao.exception.AssociadoNotFound;
import desafio.votacao.exception.PautaNotFound;
import desafio.votacao.model.Associado;
import desafio.votacao.model.Pauta;
import desafio.votacao.model.SessaoVotacao;
import desafio.votacao.model.Voto;
import desafio.votacao.repository.AssociadoRepository;
import desafio.votacao.repository.PautaRepository;
import desafio.votacao.repository.SessaoVotacaoRepository;
import desafio.votacao.repository.VotoRepository;
import desafio.votacao.service.utils.Elegibilidade;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static desafio.votacao.ConstantTest.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@Profile("test")
class SessaoServiceImplTest {


    @InjectMocks
    private SessaoServiceImpl sessaoService;

    @Mock
    private ModelMapper modelMapper;


    @Mock
    private AssociadoRepository associadoRepository;
    @Mock
    private PautaRepository pautaRepository;
    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    @Mock
    private Elegibilidade elegibilidade;


    @Mock
    private VotoRepository votoRepository;


    @Test
    void cadastrarPauta() {

        when(modelMapper.map(PAUTA_DTO, Pauta.class)).thenReturn(PAUTA);
        when(modelMapper.map(PAUTA, PautaDTO.class)).thenReturn(PAUTA_DTO);
        when(pautaRepository.save(PAUTA)).thenReturn(PAUTA);


        PautaDTO pautaDTO = sessaoService.cadastrarPauta(PAUTA_DTO);


        assertEquals(PAUTA_DTO, pautaDTO);

        verify(pautaRepository, times(1)).save(PAUTA);
    }

    @Test
    void deveAbrirSessaoComInformacoesCorretas() {

        when(modelMapper.map(REQUEST_SESSAO_VOTACAO_DTO, SessaoVotacao.class)).thenReturn(SESSAO_VOTACAO);
        when(modelMapper.map(SESSAO_VOTACAO, ResponseSessaoVotacaoDTO.class)).thenReturn(RESPONSE_SESSAO_VOTACAO_DTO);
        when(pautaRepository.findById(2L)).thenReturn(Optional.of(PAUTA));

        ResponseSessaoVotacaoDTO responseSessaoVotacaoDTO = sessaoService.abrirSessao(REQUEST_SESSAO_VOTACAO_DTO);


        assertNotNull(responseSessaoVotacaoDTO);
        assertEquals(RESPONSE_SESSAO_VOTACAO_DTO, responseSessaoVotacaoDTO);
        verify(sessaoVotacaoRepository, times(1)).save(SESSAO_VOTACAO);
    }

    @Test
    void naoDeveAbrirSessaoIdNaoEncontrado() {

        when(modelMapper.map(REQUEST_SESSAO_VOTACAO_DTO, SessaoVotacao.class)).thenReturn(SESSAO_VOTACAO);
        when(modelMapper.map(SESSAO_VOTACAO, ResponseSessaoVotacaoDTO.class)).thenReturn(RESPONSE_SESSAO_VOTACAO_DTO);
        when(pautaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(PautaNotFound.class, () -> sessaoService.abrirSessao(REQUEST_SESSAO_VOTACAO_DTO));

    }

    @Test
    void deveReceberVotosComInformacoesCorretas() {

        when(modelMapper.map(REQUEST_VOTOS_DTO, Voto.class)).thenReturn(VOTO);
        when(modelMapper.map(ASSOCIADO, AssociadoDTO.class)).thenReturn(ASSOCIADO_DTO);
        when(associadoRepository.findById(1L)).thenReturn(Optional.of(ASSOCIADO));


        AssociadoDTO associadoDTO = sessaoService.receberVotos(REQUEST_VOTOS_DTO, 1L);


        assertNotNull(associadoDTO);
        assertEquals(ASSOCIADO_DTO, associadoDTO);
        verify(associadoRepository, times(1)).save(ASSOCIADO);
        verify(votoRepository, times(1)).save(VOTO);
    }

    @Test
    void naoDeveReceberVotosIdNaoEncontrado() {

        when(modelMapper.map(REQUEST_VOTOS_DTO, Voto.class)).thenReturn(VOTO);
        when(modelMapper.map(ASSOCIADO, AssociadoDTO.class)).thenReturn(ASSOCIADO_DTO);
        when(associadoRepository.findById(2L)).thenReturn(Optional.empty());


        assertThrows(AssociadoNotFound.class, () -> sessaoService.receberVotos(REQUEST_VOTOS_DTO, 1L));


    }


    @Test
    void testContabilizarVotos() {
        // Mock dos votos
        List<Voto> votos = new ArrayList<>();
        votos.add(Voto.builder().voto("sim").build());
        votos.add(Voto.builder().voto("sim").build());
        votos.add(Voto.builder().voto("não").build());
        votos.add(Voto.builder().voto("não").build());

        // Mock do votoRepository para retornar a lista de votos
        when(votoRepository.findAll()).thenReturn(votos);

        // Chama o método que será testado
        ContabilizaVotosDto contabilizaVotosDto = sessaoService.contabilizarVotos();

        // Verifica se os votos foram contabilizados corretamente
        assertEquals(2, contabilizaVotosDto.getVotosSim());
        assertEquals(2, contabilizaVotosDto.getVotosNao());
    }

    @Test
    void testCadastrarAssociado() {

        REQUEST_ASSOCIADO_DTO.setPautasId(Arrays.asList(1L, 2L));

        Pauta pauta1 = new Pauta();
        pauta1.setId(1L);
        Pauta pauta2 = new Pauta();
        pauta2.setId(2L);

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta1));
        when(pautaRepository.findById(2L)).thenReturn(Optional.of(pauta2));

        when(modelMapper.map(REQUEST_ASSOCIADO_DTO, Associado.class)).thenReturn(ASSOCIADO);
        when(modelMapper.map(ASSOCIADO, AssociadoDTO.class)).thenReturn(ASSOCIADO_DTO);

        AssociadoDTO associadoDTO = sessaoService.cadastrarAssociado(REQUEST_ASSOCIADO_DTO);


        assertEquals(ASSOCIADO_DTO, associadoDTO);


        verify(elegibilidade).sortearElegibilidade(ASSOCIADO);
        verify(associadoRepository).save(ASSOCIADO);
        verify(modelMapper).map(ASSOCIADO, AssociadoDTO.class);
    }


    @Test
    void naoDeveCadastrarAssociadoIdInvlaido() {

        REQUEST_ASSOCIADO_DTO.setPautasId(Arrays.asList(1L, 2L));

        Pauta pauta1 = new Pauta();
        pauta1.setId(1L);

        when(pautaRepository.findById(1L)).thenReturn(Optional.of(pauta1));
        when(pautaRepository.findById(2L)).thenReturn(Optional.empty());

        when(modelMapper.map(REQUEST_ASSOCIADO_DTO, Associado.class)).thenReturn(ASSOCIADO);
        when(modelMapper.map(ASSOCIADO, AssociadoDTO.class)).thenReturn(ASSOCIADO_DTO);

        assertThrows(PautaNotFound.class, () -> sessaoService.cadastrarAssociado(REQUEST_ASSOCIADO_DTO));


    }

}