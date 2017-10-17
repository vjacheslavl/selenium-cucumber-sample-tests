package com.example.at.stepdefs;

import com.example.at.support.rest.ApplicationEndpoints;
import com.example.at.support.rest.RestClient;
import com.example.at.support.rest.dto.UserListResponse;
import com.example.at.support.rest.dto.UserPayload;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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
                user.setExtdata("{}");
                applicationRestClient.createUser(user);
            }
        }
    }

    @When("^user \"([^\"]*)\" is modified$")
    public void userIsModified(String userName) throws Throwable {
        UserListResponse allUsers = applicationRestClient.getAllUsers();
        UserPayload foundUser = allUsers.getItems().stream().filter(u -> userName.equals(u.getFirstname())).findFirst().orElseThrow(RuntimeException::new);
        foundUser.setFirstname("Modified cucumber user");
        applicationRestClient.modifyUser(foundUser);


    }

    @Then("^user list size is \"([^\"]*)\"$")
    public void userListSizeIs(int listSize) throws Throwable {
        assertThat(applicationRestClient.getAllUsers().getItems().size()).isEqualTo(listSize);
    }

    @Then("^user with surname \"([^\"]*)\" firstName is \"([^\"]*)\"$")
    public void userNameIs(String userSurname, String firstName) throws Throwable {
        UserListResponse allUsers = applicationRestClient.getAllUsers();
        UserPayload foundUser = allUsers.getItems().stream().filter(u -> userSurname.equals(u.getLastname())).findFirst().orElseThrow(RuntimeException::new);
        assertThat(foundUser.getFirstname()).isEqualTo(firstName);
    }

    @When("^all users are deleted$")
    public void allUsersAreDeleted() throws Throwable {
        UserListResponse allUsers = applicationRestClient.getAllUsers();
        for (UserPayload singleUser : allUsers.getItems()) {
            applicationRestClient.deleteById(singleUser);
        }
    }
}
