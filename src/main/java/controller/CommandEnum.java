package controller;

/**
 * Created with IntelliJ IDEA.
 * User: ru
 * Date: 03.06.15
 * Time: 23:13
 * To change this template use File | Settings | File Templates.
 */

import controller.commands.*;
import controller.commands.authentication.AuthenticationCommand;
import controller.commands.authentication.ChangePasswordCommand;
import controller.commands.authentication.LogOutCommand;

/**
 * enum with names of classes
 */
public enum CommandEnum {
    AUTHENTICATION {
        {
            this.command = new AuthenticationCommand();
        }
    },
    OWNER {
        {
            this.command = new OwnerCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogOutCommand();
        }
    },
    CHANGE_PASSWORD {
        {
            this.command = new ChangePasswordCommand();
        }
    },
    FORESTER {
        {
            this.command = new ForesterCommand();
        }
    },
    TASK {
        {
            this.command = new TaskCommand();
        }
    },
    EXECUTE_TASK {
        {
            this.command = new ExecuteTaskCommand();
        }
    };

    ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
