package br.ufes.enumeracoes;

/**
 *
 * @author rborges
 */
public enum UfEnum {
    
    AC(0), AL(1), AM(2), AP(3), BA(4), CE(5), DF(6), ES(7), GO(8),
    MA(9), MT(10), MS(11), MG(12), PA(13), PB(14), PR(15), PE(16), PI(17),
    RN(18), RS(19), RJ(20), RO(21), RR(22), SC(23), SP(24), SE(25), TO(26);
    
    private final int codigo;
        
    private UfEnum(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
    
}
