package cmcgee;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Goal which touches a timestamp file.
 *
 * @goal initializeLogger
 * 
 * @phase generate-sources
 */
@Mojo(name = "initializeLogger")
public class UtilPlugin extends AbstractMojo
{

    public void execute() throws MojoExecutionException
    {
        int ct = 1;
        String name  = "LOG_";
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDate.now());
        while (new File(name + date + "_" + ct).exists())
        {
            ct++;
        }
        System.setProperty("logFile", name + date + "_" + ct);
    }
}
