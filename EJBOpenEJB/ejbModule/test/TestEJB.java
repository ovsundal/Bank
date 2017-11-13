package test;
public class TestEJB {
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		 
		doTest();
	}
	
	public static void doTest()throws Exception{
		PersonTest pt = new PersonTest();
		KundeImportTest kit = new KundeImportTest();
		System.out.println("Setting up remote beans...");
		pt.setUp();
		kit.setUp();
		System.out.println("Adding persons using Personer-bean from PersonTest...");
		pt.addPerson();
		pt.list();
		System.out.println("Adding persons using KundeImport-bean from KundeImportTest...");
		kit.testNewKunde();
		pt.list();
		pt.remove();
		pt.tearDown();
		kit.tearDown();}
	}
	 