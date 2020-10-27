package hu.adsd.projects;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

class ProjectTest
{
    @Test
    void testProjectConfigurations()
    {
        Project project = new Project();
        // GetProjectConfigurations tests if size equals 1.
        assertEquals(1, project.getProjectConfigurations().size());
    }
}