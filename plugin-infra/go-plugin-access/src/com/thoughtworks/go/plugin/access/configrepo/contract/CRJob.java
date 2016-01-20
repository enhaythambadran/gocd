package com.thoughtworks.go.plugin.access.configrepo.contract;

import com.thoughtworks.go.plugin.access.configrepo.ErrorCollection;
import com.thoughtworks.go.plugin.access.configrepo.contract.tasks.CRTask;
import com.thoughtworks.go.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;

import java.util.*;

public class CRJob extends CRBase {
    private String name;
    private Collection<CREnvironmentVariable> environment_variables = new ArrayList<>();
    private Collection<CRTab> tabs = new ArrayList<>();
    private Collection<String> resources = new ArrayList<>();
    private Collection<CRArtifact> artifacts = new ArrayList<>();
    private Collection<CRPropertyGenerator> artifactPropertiesGenerators = new ArrayList<>();

    private Boolean runOnAllAgents;
    private Integer runInstanceCount;
    private Integer timeout;

    private List<CRTask> tasks = new ArrayList<>();

    public CRJob()
    {
    }
    public CRJob(String name, CRTask... tasks)
    {
        this.name = name;
        this.tasks = Arrays.asList(tasks);
    }

    @Override
    public void getErrors(ErrorCollection errors,String parentLocation) {
        String location = this.getLocation(parentLocation);
        errors.checkMissing(location,"name",name);
        validateEnvironmentVariableUniqueness(errors,location);
        validateTabs(errors,location);
        validateArtifacts(errors,location);
        validateProperties(errors,location);
        validateTasks(errors,location);
    }

    private void validateTasks(ErrorCollection errors, String location) {
        errors.checkMissing(location,"tasks",tasks);
        if(tasks != null)
            for(CRTask task : tasks)
            {
                task.getErrors(errors,location);
            }
    }

    private void validateProperties(ErrorCollection errors, String location) {
        if(artifactPropertiesGenerators != null)
            for(CRPropertyGenerator gen : artifactPropertiesGenerators)
            {
                gen.getErrors(errors,location);
            }
    }

    private void validateArtifacts(ErrorCollection errors, String location) {
        if(artifacts == null)
            return;
        for(CRArtifact artifact : artifacts)
        {
            artifact.getErrors(errors,location);
        }
    }

    private void validateTabs(ErrorCollection errors, String location) {
        if(tabs == null)
            return;
        for(CRTab tab : tabs)
        {
            tab.getErrors(errors,location);
        }
    }

    private void validateEnvironmentVariableUniqueness(ErrorCollection errors, String location) {
        HashSet<String> keys = new HashSet<>();
        for(CREnvironmentVariable var : environment_variables)
        {
            String error = var.validateNameUniqueness(keys);
            if(error != null)
                errors.addError(location,error);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        CRJob that = (CRJob)o;
        if(that == null)
            return  false;

        if (name != null ? !name.equals(that.getName()) : that.getName() != null) {
            return false;
        }

        if (environment_variables != null ? !CollectionUtils.isEqualCollection(this.environment_variables, that.environment_variables) : that.environment_variables != null) {
            return false;
        }
        if (tabs != null ? !CollectionUtils.isEqualCollection(this.tabs, that.tabs) : that.tabs != null) {
            return false;
        }
        if (resources != null ? !CollectionUtils.isEqualCollection(this.resources, that.resources) : that.resources != null) {
            return false;
        }
        if (artifacts != null ? !CollectionUtils.isEqualCollection(this.artifacts, that.artifacts) : that.artifacts != null) {
            return false;
        }
        if (artifactPropertiesGenerators != null ? !CollectionUtils.isEqualCollection(this.artifactPropertiesGenerators, that.artifactPropertiesGenerators) : that.artifactPropertiesGenerators != null) {
            return false;
        }
        if (tasks != null ? this.tasks.size() != that.tasks.size() : that.tasks != null) {
            return false;
        }
        for(int i = 0 ; i< this.tasks.size(); i++)
        {
            if(!tasks.get(i).equals(that.tasks.get(i)))
                return false;
        }
        if(this.runOnAllAgents != that.runOnAllAgents)
            return false;
        if(this.runInstanceCount != that.runInstanceCount)
            return false;
        if(this.timeout != that.timeout)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (name != null ? name.hashCode() : 0);
        result = 31 * result + (tabs != null ? tabs.size() : 0);
        result = 31 * result + (resources != null ? resources.size() : 0);
        result = 31 * result + (environment_variables != null ? environment_variables.size() : 0);
        result = 31 * result + (resources != null ? resources.size() : 0);
        result = 31 * result + (artifacts != null ? artifacts.size() : 0);
        result = 31 * result + (artifactPropertiesGenerators != null ? artifactPropertiesGenerators.size() : 0);
        return result;
    }

    public void addTask(CRTask task)
    {
        tasks.add(task);
    }

    public void addEnvironmentVariable(String key,String value){
        CREnvironmentVariable variable = new CREnvironmentVariable(key);
        variable.setValue(value);
        this.environment_variables.add(variable);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<CREnvironmentVariable> getEnvironmentVariables() {
        return environment_variables;
    }

    public void setEnvironmentVariables(Collection<CREnvironmentVariable> environmentVariables) {
        this.environment_variables = environmentVariables;
    }

    public Collection<CRTab> getTabs() {
        return tabs;
    }

    public void setTabs(Collection<CRTab> tabs) {
        this.tabs = tabs;
    }

    public Collection<String> getResources() {
        return resources;
    }

    public void setResources(Collection<String> resources) {
        this.resources = resources;
    }

    public Collection<CRArtifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(Collection<CRArtifact> artifacts) {
        this.artifacts = artifacts;
    }

    public Collection<CRPropertyGenerator> getArtifactPropertiesGenerators() {
        return artifactPropertiesGenerators;
    }

    public void setArtifactPropertiesGenerators(Collection<CRPropertyGenerator> artifactPropertiesGenerators) {
        this.artifactPropertiesGenerators = artifactPropertiesGenerators;
    }

    public boolean isRunOnAllAgents() {
        return runOnAllAgents;
    }

    public void setRunOnAllAgents(boolean runOnAllAgents) {
        this.runOnAllAgents = runOnAllAgents;
    }

    public int getRunInstanceCount() {
        return runInstanceCount;
    }

    public void setRunInstanceCount(int runInstanceCount) {
        this.runInstanceCount = runInstanceCount;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public List<CRTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<CRTask> tasks) {
        this.tasks = tasks;
    }

    public void addResource(String resource) {
        this.resources.add(resource);
    }

    public void addTab(CRTab tab) {
        this.tabs.add(tab);
    }

    public void addProperty(CRPropertyGenerator property) {
        this.artifactPropertiesGenerators.add(property);
    }

    public String validateNameUniqueness(HashSet<String> names) {
        if(names.contains(this.getName()))
            return String.format("Job %s is defined more than once",this.getName());
        else
            names.add(this.getName());
        return null;
    }

    @Override
    public String getLocation(String parent) {
        return null;
    }
}
