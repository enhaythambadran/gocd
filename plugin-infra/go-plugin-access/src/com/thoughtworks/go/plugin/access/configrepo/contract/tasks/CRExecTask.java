package com.thoughtworks.go.plugin.access.configrepo.contract.tasks;

import com.thoughtworks.go.plugin.access.configrepo.ErrorCollection;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CRExecTask extends CRTask  {
    public static final String TYPE_NAME = "exec";

    private String command ;
    private String working_directory;
    private Long timeout ;
    private List<String> args = new ArrayList<>();

    public CRExecTask() {
        super(TYPE_NAME);
    }
    public CRExecTask(String command) {
        super(TYPE_NAME);
        this.command = command;
    }
    public CRExecTask(String command,String workingDirectory, Long timeout,CRRunIf runIf,CRTask onCancel,String... args) {
        super(TYPE_NAME,runIf,onCancel);
        this.command = command;
        this.working_directory = workingDirectory;
        this.timeout = timeout;
        this.args = Arrays.asList(args);
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getWorkingDirectory() {
        return working_directory;
    }

    public void setWorkingDirectory(String workingDirectory) {
        this.working_directory = workingDirectory;
    }

    public Long getTimeout() {
        return timeout;
    }

    public void setTimeout(Long timeout) {
        this.timeout = timeout;
    }

    public List<String> getArgs() {
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }


    public void addArgument(String arg) {
        this.args.add(arg);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        CRExecTask that = (CRExecTask)o;
        if(that == null)
            return  false;

        if(!super.equals(that))
            return false;

        if (command != null ? !command.equals(that.command) : that.command != null) {
            return false;
        }
        if (working_directory != null ? !working_directory.equals(that.working_directory) : that.working_directory != null) {
            return false;
        }
        if (timeout != null ? !timeout.equals(that.timeout) : that.timeout != null) {
            return false;
        }
        if (args != null ? !CollectionUtils.isEqualCollection(this.args, that.args) : that.args != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (command != null ? command.hashCode() : 0);
        result = 31 * result + (working_directory != null ? working_directory.hashCode() : 0);
        result = 31 * result + (timeout != null ? timeout.hashCode() : 0);
        return result;
    }

    @Override
    public void getErrors(ErrorCollection errors, String parentLocation) {

    }

    @Override
    public String getLocation(String parent) {
        return null;
    }
}