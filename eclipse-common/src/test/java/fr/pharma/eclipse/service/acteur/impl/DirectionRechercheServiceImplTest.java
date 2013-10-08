package fr.pharma.eclipse.service.acteur.impl;

import java.util.TreeSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import fr.pharma.eclipse.dao.common.GenericDao;
import fr.pharma.eclipse.domain.model.acteur.DirectionRecherche;
import fr.pharma.eclipse.domain.model.acteur.Personne;
import fr.pharma.eclipse.domain.model.suivi.acteur.PersonneSuivi;
import fr.pharma.eclipse.factory.suivi.SuiviFactory;
import fr.pharma.eclipse.service.acl.AclService;
import fr.pharma.eclipse.service.acteur.helper.PasswordEncoderHelper;
import fr.pharma.eclipse.validator.save.SaveValidator;
import fr.pharma.eclipse.validator.save.impl.PersonneSaveValidator;

/**
 * Classe en charge de tester le service de gestion des directionRecherches.
 * @author Netapsys
 * @version $Revision$ $Date$
 */
public class DirectionRechercheServiceImplTest {

    /**
     * Service de gestion de directionRecherche à tester.
     */
    private PersonneServiceImpl<DirectionRecherche> service;

    /**
     * Dao mocké.
     */
    private GenericDao<DirectionRecherche> mockDao;

    /**
     * Suivi Factory mocké.
     */
    private SuiviFactory<PersonneSuivi> mockSuiviFactory;

    /**
     * Validator de sauvegarde mocké.
     */
    private SaveValidator<DirectionRecherche> mockSaveValidator;

    /**
     * Password encoder mocké.
     */
    private PasswordEncoderHelper mockPasswordEncoderHelper;

    /**
     * Validator de personne mocké.
     */
    private PersonneSaveValidator mockPersonneSaveValidator;

    /**
     * Service des acls mocké.
     */
    private AclService mockAclService;

    /**
     * Méthode en charge d'initialiser les données de test.
     */
    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        this.mockDao = Mockito.mock(GenericDao.class);
        this.service = new PersonneServiceImpl<DirectionRecherche>(this.mockDao);
        this.mockSuiviFactory = Mockito.mock(SuiviFactory.class);
        this.service.setPersonneSuiviFactory(this.mockSuiviFactory);
        this.mockSaveValidator = Mockito.mock(SaveValidator.class);
        this.service.setSaveValidator(this.mockSaveValidator);
        this.mockPasswordEncoderHelper = Mockito.mock(PasswordEncoderHelper.class);
        this.service.setPasswordEncoderHelper(this.mockPasswordEncoderHelper);
        this.mockPersonneSaveValidator = Mockito.mock(PersonneSaveValidator.class);
        this.service.setPersonneSaveValidator(this.mockPersonneSaveValidator);
        this.mockAclService = Mockito.mock(AclService.class);
        this.service.setAclService(this.mockAclService);
    }

    /**
     * Méthode en charge de purger les données de test.
     */
    @After
    public void end() {
        this.mockDao = null;
        this.service = null;
        this.mockSuiviFactory = null;
        this.mockSaveValidator = null;
        this.mockPasswordEncoderHelper = null;
        this.mockPersonneSaveValidator = null;
        this.mockAclService = null;
    }

    /**
     * Méthode en charge de tester l'initialisation des données de test.
     */
    @Test
    public void testInit() {
        Assert.assertNotNull(this.service);
        Assert.assertNotNull(this.mockDao);
        Assert.assertNotNull(this.mockSuiviFactory);
        Assert.assertNotNull(this.mockSaveValidator);
        Assert.assertNotNull(this.mockPasswordEncoderHelper);
        Assert.assertNotNull(this.mockPersonneSaveValidator);
        Assert.assertNotNull(this.mockAclService);
    }

    /**
     * Méthode en charge de tester la sauvegarde des personnes avec passage dans
     * le validator.
     */
    @Test
    public void testSavePersonneWithValidator() {
        final DirectionRecherche directionRecherche = Mockito.mock(DirectionRecherche.class);
        final PersonneSuivi suivi = new PersonneSuivi();
        Mockito.when(this.mockDao.reattach(directionRecherche)).thenReturn(directionRecherche);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(directionRecherche.getModifs()).thenReturn(new TreeSet<PersonneSuivi>());
        Mockito.when(this.mockDao.save(directionRecherche)).thenReturn(directionRecherche);
        final Personne result = this.service.save(directionRecherche);
        Mockito.verify(this.mockDao).reattach(directionRecherche);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Mockito.verify(this.mockSaveValidator).validate(directionRecherche, this.service);
        Mockito.verify(this.mockPasswordEncoderHelper).encodePassword(directionRecherche);
        Mockito.verify(this.mockPersonneSaveValidator).validate(directionRecherche);
        Assert.assertEquals(1, result.getModifs().size());
    }

    /**
     * Méthode en charge de tester la sauvegarde des personnes sans passage dans
     * le validator.
     */
    @Test
    public void testSavePersonneWithoutValidator() {
        this.service.setSaveValidator(null);
        final DirectionRecherche directionRecherche = Mockito.mock(DirectionRecherche.class);
        final PersonneSuivi suivi = new PersonneSuivi();
        Mockito.when(this.mockDao.reattach(directionRecherche)).thenReturn(directionRecherche);
        Mockito.when(this.mockSuiviFactory.getInitializedObject()).thenReturn(suivi);
        Mockito.when(directionRecherche.getModifs()).thenReturn(new TreeSet<PersonneSuivi>());
        Mockito.when(this.mockDao.save(directionRecherche)).thenReturn(directionRecherche);
        final Personne result = this.service.save(directionRecherche);
        Mockito.verify(this.mockDao).reattach(directionRecherche);
        Mockito.verify(this.mockSuiviFactory).getInitializedObject();
        Mockito.verify(this.mockPasswordEncoderHelper).encodePassword(directionRecherche);
        Mockito.verify(this.mockPersonneSaveValidator).validate(directionRecherche);
        Assert.assertEquals(1, result.getModifs().size());
    }

}
