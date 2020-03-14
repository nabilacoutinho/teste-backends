package model;

public enum WarrantyProvinceEnum {
ACRE("AC", "BR"),
AMAPA("AP", "BR"),
AMAZONAS("AM", "BR"),
MARANHAO("MA", "BR"),
RONDONIA("RO", "BR"),
RORAIMA("RR", "BR"),
TOCANTINS("TO", "BR"),
PIAUI("PI", "BR"),
CEARA("CE", "BR"),
RIO_GRANDE_DO_NORTE("RN", "BR"),
PARAIBA("PB", "BR"),
PERNAMBUCO("PE", "BR"),
ALAGOAS("AL", "BR"),
SERGIPE("SE", "BR"),
BAHIA("BA", "BR"),
MINAS_GERAIS("MG", "BR"),
ESPIRITO_SANTO("ES", "BR"),
RIO_DE_JANEIRO("RJ", "BR"),
SAO_PAULO("SP", "BR"),
PARANA("PR", "BR"),
SANTA_CATARINA("SC", "BR"),
RIO_GRANDE_DO_SUL("RS", "BR"),
MATO_GROSSO_DO_SUL("MS", "BR"),
MATO_GROSSO("MT", "BR"),
GOIAS("GO", "BR"),
DISTRITO_FEDERAL("DF", "BR");
	
	private static String BRAZIL = "BR";
	
	private String code;
	private String country;
	private Boolean allowed;
	
	private WarrantyProvinceEnum(String code, String country) {
		this.code = code;
		this.country = country;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getCountry() {
		return country;
	}
	
	public boolean isAllowed() {
		if (allowed == null) {
			allowed = !BRAZIL.equals(country) || !(PARANA.getCode().equals(code) || SANTA_CATARINA.getCode().equals(code) || RIO_GRANDE_DO_SUL.getCode().equals(code));
		}
		
		return allowed;
	}
	
	public static WarrantyProvinceEnum getByCode(String code) {
		if (code.isBlank()) {
			return null;
		}
		
		for (WarrantyProvinceEnum warrantyProvince : WarrantyProvinceEnum.values()) {
			if (warrantyProvince.getCode().equals(code)) {
				return warrantyProvince;
			}
		}
		
		return null;
	}
	
}
