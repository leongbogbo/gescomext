package com.mincom.gescomext.config;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import groovyjarjarpicocli.CommandLine.ExitCode;

public class CalculeCodesExportation {
    
    Date dateDAy = new Date();
    public static String getCodeImportExport(String regimeCom, String CCcode, Integer numOrdre) {
		    List<TableauCorrespondance> tableauCorrespondance = TableauCorrespondance.getAlphaBaseDix();			
			String regCommerce = regimeCom;
			String[] deCoupeRegCommerce = regCommerce.split("-");
			int codeJury = Integer.parseInt(deCoupeRegCommerce[2]);
			// trouver la clé
			char keys=' ';
			 for (TableauCorrespondance donnees : tableauCorrespondance){
				 if(donnees.getBaseDix() == codeJury){
					 keys = donnees.getCorrepondance();
				 }
			 }
			 // DETERMINER A1
			// Premier chiffre du numero d'ordre du RCCm
			 int lettreRegCom = Integer.parseInt(deCoupeRegCommerce[5].substring(0,1));
			 // Dernier chiffre numerique du CC 
			 int lettreCC = Integer.parseInt(CCcode.substring(6,7));
			 // cle trouvé converti en Base10 
			 int numCle=0;
			for (TableauCorrespondance donnees : tableauCorrespondance){
				if(donnees.getLettre() == keys){
					numCle = donnees.getBaseDix();
				}
			}
			// Calcule de la lettre A1
			int lettreA1 = lettreRegCom + lettreCC + numCle;
			if((int) (Math.log10(lettreA1)+1) >1){
				lettreA1 = lettreA1%9;
			}

			// Designation de la lettre A2
			int lettreA2 = 1;
			// Designation de la lettre A3-A9
			Integer chiffreA3A9 = numOrdre;

		return String.valueOf(keys)+String.valueOf(lettreA1)+String.valueOf(lettreA2)+String.valueOf(chiffreA3A9);
	}
   
        public static String getCodeImportExportWithOutRCCM(String CCcode, Integer numOrdre) {
            List<TableauCorrespondance> tableauCorrespondance = TableauCorrespondance.getAlphaBaseDix();
            char lettreCC = CCcode.substring(7,8).charAt(0);
            // trouver la clé
            char keys=' ';
            for (TableauCorrespondance donnees : tableauCorrespondance){
                if(donnees.getLettre() == lettreCC){
                    int numCle = donnees.getBaseDix();

                    if(donnees.getBaseDix() == numCle){
                        keys = donnees.getCorrepondance();
                    }
                }
            }
            // DETERMINER A1
            // cle trouvé converti en Base10
            int numKeys=0;
            for (TableauCorrespondance donnees : tableauCorrespondance){
                if(donnees.getLettre() == keys){
                    numKeys = donnees.getBaseDix();
                }
            }
            // Dernier chiffre numerique du CC 
            int chiffreCC = Integer.parseInt(CCcode.substring(6,7));
            
            // Calcule de la lettre A1
            int lettreA1 = numKeys + chiffreCC + 1;
            if((int) (Math.log10(lettreA1)+1) >1){
                lettreA1 = lettreA1%9;
            }

            // Designation de la lettre A2 (pas RCCM)
            int lettreA2 = 0;
            // Designation de la lettre A3-A9
            Integer chiffreA3A9 = numOrdre;
            
        return String.valueOf(keys)+String.valueOf(lettreA1)+String.valueOf(lettreA2)+String.valueOf(chiffreA3A9);
    }

    public static String getCodeFixcal(Integer lettreB, Integer numOrdre) {
        List<TableauJoursSemaine> tableauJoursSemaine = TableauJoursSemaine.getJoursSemaines();

        SimpleDateFormat dateJour = new SimpleDateFormat("EEEE");
        String jour = dateJour.format(new Date());

        SimpleDateFormat dateAn = new SimpleDateFormat("yyyy");
        String annee = dateAn.format(new Date());

        int lettreA=0;
        for (TableauJoursSemaine donnees : tableauJoursSemaine){
            if(donnees.getJour().equals(jour)){
                lettreA = donnees.getNumero();
            }
        }
        
        String indiceAnn = annee.substring(2,4);

        

        return String.valueOf(lettreA)+String.valueOf(lettreB)+String.valueOf(numOrdre)+indiceAnn;
    }
    
    public static String getCodeOccasionnel(String codeStruc, Integer numOrdre) {
        List<TableauCodeTypeStructure> tableauCodeTypeStructure = TableauCodeTypeStructure.getCodeTypeStructures();

        SimpleDateFormat dateAn = new SimpleDateFormat("yyyy");
        String annee = dateAn.format(new Date());

        int lettreDec=0;
        String lettreHexa="";
        for (TableauCodeTypeStructure donnees : tableauCodeTypeStructure){
            if(donnees.codes.equals(codeStruc)){
                lettreDec = donnees.getDecimal();
                lettreHexa = donnees.getHexadecimal();
            }
        }

        String indiceAnn = annee.substring(2,4);

        return String.valueOf(lettreDec)+indiceAnn+String.valueOf(lettreHexa)+String.valueOf(numOrdre);
    }
    
    public static String getLeveeGage(String usage, String chassis, String typeGage, Integer numOrdre) {
        List<TableauCodeTypeStructure> tableauCodeTypeStructure = TableauCodeTypeStructure.getCodeTypeStructures();

        SimpleDateFormat dateAn = new SimpleDateFormat("yyyy");
        String annee = dateAn.format(new Date());
        String indiceAnn = annee.substring(2,4);
        
        char lettreX=' ';
        if(usage.equals("personnel")){
            lettreX='U';
        }else if(usage.equals("commercial")){
            lettreX='V';
        }   
		
        String lettreT = chassis.substring(0,3);
        StringBuilder strb = new StringBuilder(lettreT);
        lettreT = strb.reverse().toString();

        char lettreQ=' ';
        if(typeGage.equals("ordinaire")){
            lettreQ='R';
        }else if(typeGage.equals("exceptionnel")){
            lettreQ='X';
        }  


        return String.valueOf(indiceAnn)+String.valueOf(lettreX)+String.valueOf(lettreT)+String.valueOf(numOrdre)+String.valueOf(lettreQ);
    }

}
