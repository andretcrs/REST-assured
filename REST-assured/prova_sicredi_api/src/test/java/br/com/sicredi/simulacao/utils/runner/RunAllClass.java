package br.com.sicredi.simulacao.utils.runner;
import br.com.sicredi.simulacao.cenarios.restricoes.RestricoesTest;
import br.com.sicredi.simulacao.cenarios.simulacoes.AlterarSimulacoesTest;
import br.com.sicredi.simulacao.cenarios.simulacoes.CadastrarSimulacoesTest;
import br.com.sicredi.simulacao.cenarios.simulacoes.ConsultarSimulacoesTest;
import br.com.sicredi.simulacao.cenarios.simulacoes.RemoverSimulacoesTest;
import org.junit.runner.RunWith;
import br.com.sicredi.simulacao.base.BaseTest;
import org.junit.runners.Suite;

@RunWith(org.junit.runners.Suite.class)

@Suite.SuiteClasses({
        CadastrarSimulacoesTest.class,
        ConsultarSimulacoesTest.class,
        RemoverSimulacoesTest.class,
        RestricoesTest.class,
        AlterarSimulacoesTest.class,
})
public class RunAllClass extends BaseTest {

}
