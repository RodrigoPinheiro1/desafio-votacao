package desafio.votacao.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import desafio.votacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PautaServiceImpl implements PautaService {



    private final ModelMapper modelMapper;

    private final ObjectMapper objectMapper;






}
