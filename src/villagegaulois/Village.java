package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private Marche marche;;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMaximum, int nbEtals) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMaximum];
		this.marche = new Marche(nbEtals);
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
		private Etal etals[] = new Etal[nbEtals];
		
		public Marche(int nbEtals) {
			this.nbEtals = nbEtals;
			for(int i=0;i<nbEtals;i++) {
				etals[i] = null;
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
			for(int i = 0; i < nbEtals;i++) {
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
}