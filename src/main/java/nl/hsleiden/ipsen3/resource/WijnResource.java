package nl.hsleiden.ipsen3.resource;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import liquibase.util.file.FilenameUtils;
import nl.hsleiden.ipsen3.core.User;
import nl.hsleiden.ipsen3.core.Wijn;
import nl.hsleiden.ipsen3.core.helper.WijnImage;
import nl.hsleiden.ipsen3.dao.WijnDAO;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.FlushMode;
import org.joda.time.DateTime;
import org.joda.time.Instant;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Daan on 30-Nov-15.
 */
@Path("/wijnen")
@Produces(MediaType.APPLICATION_JSON)
public class WijnResource {

    private final WijnDAO dao;
    private final String uploadDir;

    public WijnResource(WijnDAO dao, String uploadDir) {
        this.dao = dao;
        this.uploadDir = uploadDir;
    }

    @GET
    @Timed
    @UnitOfWork
    @Path("/{id}")
    public Wijn getById(@PathParam("id") Long id) {
        return dao.findById(id);
    }

    @GET
    @Timed
    @UnitOfWork
    public List<Wijn> findAll() {
        return dao.findAll();
    }

    @POST
    @Timed
    @UnitOfWork
    @RolesAllowed({"beheerder", "m&s manager"})
    public long createWijn(@Auth User user, Wijn wijn) {
        return dao.create(wijn);
    }

    @POST
    @Path("/{id}")
    @Timed
    @UnitOfWork
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @RolesAllowed({"beheerder", "m&s manager"})
    public Wijn uploadImage(@Auth User user,
                            @PathParam("id") Long id,
                            @FormDataParam("file") final InputStream fileInputStream,
                            @FormDataParam("file") FormDataContentDisposition fileDetail) throws IOException {
        Wijn wijn = dao.findById(id);
        if (fileInputStream != null) {
            String fileName = Instant.now().getMillis() + "." + FilenameUtils.getExtension(fileDetail.getFileName());
            java.nio.file.Path outputPath = FileSystems.getDefault().getPath(uploadDir, fileName);
            Files.copy(fileInputStream, outputPath);

            if (!wijn.getImages().isEmpty()) {
                wijn.getImages().get(0).setUrl("uploads/" + fileName);
            } else {
                WijnImage img = new WijnImage();
                img.setUrl("uploads/" + fileName);
                wijn.getImages().add(img);
            }
        }
        dao.update(wijn);
        return wijn;
    }
}
