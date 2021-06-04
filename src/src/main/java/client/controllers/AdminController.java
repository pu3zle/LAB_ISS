package client.controllers;

import models.Drug;
import persistence.database.DrugsHibernation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class AdminController {
    private DrugsHibernation drugsHibernation;

    public AdminController(DrugsHibernation drugsHibernation) {
        this.drugsHibernation = drugsHibernation;
    }

    public List<Drug> GetAllDrugs()
    {
        return StreamSupport.stream(drugsHibernation.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Drug> GetFilteredDrugs(String sw)
    {
        List<Drug> fDrugs = new ArrayList<>();
        Iterable<Drug> drugs = drugsHibernation.findAll();
        for(Drug drug : drugs)
        {
            if(drug.getName().startsWith(sw))
            {
                fDrugs.add(drug);
            }
        }
        return fDrugs;
    }

    public Drug AddDrug(Drug drug)
    {
        return drugsHibernation.save(drug);
    }

    public void DeleteDrug(Integer id)
    {
        drugsHibernation.delete(id);
    }
}
