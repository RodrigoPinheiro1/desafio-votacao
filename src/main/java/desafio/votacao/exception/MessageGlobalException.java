package desafio.votacao.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageGlobalException {

    private LocalDateTime data;

    private Integer status;

    private String message;

    private List<ErrorValidation> errorValidation = new ArrayList<>();



}
