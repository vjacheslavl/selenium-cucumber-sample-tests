package com.example.at.stepdefs;

import com.example.at.support.rest.ApplicationEndpoints;
import com.example.at.support.rest.RestClient;
import com.example.at.support.rest.dto.UserListResponse;
import com.example.at.support.rest.dto.UserPayload;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;

import java.util.List;

import static java.util.UUID.randomUUID;
import static org.assertj.core.api.Assertions.assertThat;

public class RestStepdefs {

    private RestClient restClient = new RestClient();
    private ApplicationEndpoints applicationRestClient = restClient.createClient();

    @Given("^following user created$")
    public void followingUserCreated(List<UserPayload> users) throws Throwable {
        int i = 0;
        for (UserPayload user : users) {
            {
                i++;
                String generatedId = randomUUID().toString();
                user.setId(generatedId);
                user.setFirstname(user.getFirstname() + "-" + String.valueOf(i));
                user.setLastname(user.getLastname() + "-" + String.valueOf(i));
                applicationRestClient.createUser(user);
            }
        }
    }

    @Given("^user \"([^\"]*)\" is modified$")
    public void userIsModified(String userName) throws Throwable {
        UserListResponse allUsers = applicationRestClient.getAllUsers();
        UserPayload foundUser = allUsers.getItems().stream().filter(u -> userName.equals(u.getFirstname())).findFirst().get();
        foundUser.setFirstname("Modified cucumber user");
        applicationRestClient.modifyUser(foundUser);


    }

    @Given("^user list size is \"([^\"]*)\"$")
    public void userListSizeIs(int listSize) throws Throwable {
        assertThat(applicationRestClient.getAllUsers().getItems().size()).isEqualTo(listSize);
    }

    @And("^user with surname \"([^\"]*)\" firstName is \"([^\"]*)\"$")
    public void userNameIs(String userSurname, String firstName) throws Throwable {
        UserListResponse allUsers = applicationRestClient.getAllUsers();
        UserPayload foundUser = allUsers.getItems().stream().filter(u -> userSurname.equals(u.getLastname())).findFirst().get();
        assertThat(foundUser.getFirstname()).isEqualTo(firstName);
    }

    @And("^all users are deleted$")
    public void allUsersAreDeleted() throws Throwable {
        UserListResponse allUsers = applicationRestClient.getAllUsers();
        for (UserPayload singleUser : allUsers.getItems()) {
            applicationRestClient.deleteById(singleUser);
        }
    }
}
