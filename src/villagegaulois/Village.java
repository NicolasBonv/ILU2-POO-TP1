package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private Marche marche;
	private int nbEtals;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.nbEtals = nbEtals;
		marche = new Marche(nbEtals);
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}
	// DEBUT CLASSE INTERNE //
	public class Marche{
		int nbEtals; 
		private Etal etals[];
		
		public Marche(int nbEtals) {
			this.nbEtals = nbEtals;
			Etal etals[] = new Etal[nbEtals];
			for(int i=0 ; i<nbEtals;i++) {
				etals[i] = new Etal();
			}
		}
		void utiliserEtal(int indEtal, Gaulois vendeur, String produit, int nbProd) {
			etals[indEtal].occuperEtal(vendeur, produit, nbProd);
		}
		int trouverEtalLibre() {
			for(int i = 0; i < nbEtals;i++) {
				if (etals[i].isEtalOccupe()) {
					return i;
				}	
			}
			return -1;
		}
		Etal[] trouverEtals(String prod) {
			Etal etalsProd[] = new Etal[nbEtals];
			int j = 0;
			for(int i = 0; i < nbEtals;i++){
				if(etals[i].contientProduit(prod)) {
					etalsProd[j] = etals[i];
					j++;	
				}
			}
			return etalsProd;
		}
		Etal trouverVendeur(Gaulois gaulois) {
			for(int i = 0; i < nbEtals;i++) {
				if(etals[i].getVendeur() == gaulois) {
					return etals[i];
				}
			}
			return null;
		}
		void afficherMarche() {
			int nbEtalVide = 0 ;
			for(int i = 0; i<nbEtals;i++) {
				if(etals[i] != null) {
					etals[i].afficherEtal();
				}else {
					nbEtalVide++;
				}
			}
			if (nbEtalVide != 0) {
				System.out.println("Il reste "+ nbEtalVide +" étals non utilisés dans le marché");
			}
		}
	}
	// FIN CLASSE INTERNE //
	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}
	
	public String afficherVillageois() {
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
					+ " vivent les lÃ©gendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	
	// FONCTION FEUILLE 7 //
	public String installerVendeur(Gaulois vendeur, String produit,int nbProd) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Bonemine cherche un endroit pour vendre "+ nbProd + produit);
		chaine.append("Le vendeur "+ vendeur + " vend des "+ produit + "à l'étal "+ marche.trouverEtalLibre());
		return chaine.toString();
	}
	public String rechercherVendeursProduit(String produit) {
		Etal etalsProd[] = new Etal[nbEtals];
		StringBuilder chaine = new StringBuilder();
		etalsProd = marche.trouverEtals(produit);
		boolean isProdHere = false;
		for(int i = 0 ; i<nbEtals;i++) {
			if (etalsProd[i].contientProduit(produit)) {
				isProdHere = true;
				chaine.append("le vendeur" + etalsProd[i].getVendeur()+ "propose des"+ produit+ "au marché");
			}
		}
		if (!isProdHere) {
			chaine.append("Il n'y a pas de vendeur qui propose de "+ produit +" au marché");
		}
		return chaine.toString();
	}
	public Etal rechercherEtal(Gaulois vendeur) {
		return null;
	}
	public String partirVendeur(Gaulois vendeur) {
		return null;
	}
	public String afficherMarche() {
		return null;
	}
	// FIN FEUILLE 7 //
}