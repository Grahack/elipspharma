package fr.pharma.eclipse.domain.model.produit.document;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import fr.pharma.eclipse.domain.enums.document.TypeDocumentProduit;

/**
 * Bean métier représentant un document relatif à la fabrication d'un acte
 * pharma.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
@Entity
@DiscriminatorValue("FABRICATION")
public class DocumentFabrication extends DocumentActesPharma {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = 3552420140396626957L;

    /**
     * Constructeur par défaut.
     */
    public DocumentFabrication() {
        this.setType(TypeDocumentProduit.FABRICATION);
    }

}
