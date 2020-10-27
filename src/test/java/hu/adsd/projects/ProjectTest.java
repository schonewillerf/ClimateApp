package hu.adsd.projects;

import static org.junit.Assert.assertEquals;

class ProjectTest
{
    @org.junit.jupiter.api.Test
    void testProjectConfigurations()
    {
        Project project = new Project();
        // GetProjectConfigurations tests if size equals 1.
        assertEquals(1, project.getProjectConfigurations().size());
    }
}