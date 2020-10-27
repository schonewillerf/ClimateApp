package hu.adsd.projects;


import static org.junit.Assert.assertEquals;



class ProjectTest {
    Project project = new Project();

    @org.junit.jupiter.api.Test
    void getProjectBuildingParts() {

    }

    @org.junit.jupiter.api.Test
    void getProjectConfigurations() {

        //Assert.assertEquals( 4,project.getProjectConfigurations(i));
         assertEquals(1,project.projectConfigurations.size());
       // Assert.assertEquals(  4,project.projectConfigurations.size());

        //assertFalse(project.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void setProjectBuildingParts() {
    }

    @org.junit.jupiter.api.Test
    void addConfiguration() {
    }
// tip Raymond* test maximaal vier  configuraties en test minimaal een configuratie
    @org.junit.jupiter.api.Test
    void removeConfiguration() {





    }

    @org.junit.jupiter.api.Test
    void addProductToProject() {
    }
}