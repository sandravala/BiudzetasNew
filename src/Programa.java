public class Programa {

	public static void main(String[] args) {

		// daryti mygtuka naujas biudzetas su pavadinimu arba atidaryti biudzeta jau esanti pavadinimu ir nuskaityti faila
		// reikia atskiro metodo. arba konstruktoriaus?
		Biudzetas biudzetas = new Biudzetas();

//		biudzetas.pridetiPajamas(1, "DU", null, "bankas");
//		biudzetas.pridetiPajamas(2, null, "kazkokia tai papildoma info", "bankas");
//		biudzetas.pridetiPajamas(3, "autorine sutartis", null, "grynieji");
//		biudzetas.pridetiPajamas(4, "autorine sutartis", null, "bankas");
//		biudzetas.pridetiPajamas(5, null, "", "grynieji");
//		biudzetas.pridetiPajamas(5, null, "", "bankas");
//
//
//
//		biudzetas.pridetiIslaidas(20, null, "grynieji", "grynieji");
//
////		biudzetas.spausdintiPajamas();
//		biudzetas.spausdinti();
//
//		biudzetas.spausdinti("islaidas");
//		biudzetas.spausdinti("pajamas");
//
//		//String data = LocalDate.now().toString();
//		biudzetas.gautiIrasa(null, "2022-10-07");
//
		biudzetas.balansas();

		biudzetas.istrintiIrasa("P3");
		
// UI		
		
		//new UserInterface();
		//UserInterface.main(args);
//file read write

		//FileReadWrite.readFile("/biudzetas.csv");
	}

}
