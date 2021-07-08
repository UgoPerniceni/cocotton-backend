package fr.esgi.cocotton.profile.infrastructure.persistence;

import fr.esgi.cocotton.profile.domain.Profile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaProfileDaoTest {

    @Autowired
    JpaProfileDao jpaProfileDao;

    private String currentProfileId;
    private final String userId = "userId";

    Profile currentProfile = Profile.builder()
            .lastName("userlastName")
            .firstName("userfirstName")
            .username("currprofileName")
            .password("@_Strong-password01")
            .email("curr@mail.fr")
            .birthDate(LocalDate.of(2011, 11, 11))
            .build();

    Profile mockProfile = Profile.builder()
            .lastName("userlastName")
            .firstName("userfirstName")
            .username("mockprofileName")
            .password("@_Strong-password01")
            .email("mock@mail.fr")
            .birthDate(LocalDate.of(2011, 11, 11))
            .build();

    @Before
    public void init() {
        this.currentProfileId = jpaProfileDao.save(this.currentProfile);
    }

    @Test
    public void should_save_profile() {
        String id = jpaProfileDao.save(this.mockProfile);
        assertThat(id).isNotNull();
        jpaProfileDao.deleteById(id);
    }

    @Test
    public void should_find_profile_by_id() {
        Profile profile = jpaProfileDao.findById(this.currentProfileId).get();

        assertThat(profile).isNotNull();
        assertThat(profile.getId()).isEqualTo(this.currentProfileId);
    }

    @Test
    public void should_find_all_profiles() {
        List<Profile> profileList = jpaProfileDao.findAll();
        System.out.println(profileList.size());
        assertThat(profileList).hasSize(1);
    }

    @Test
    public void should_delete_profile_by_id() {
        String toDeleteId = jpaProfileDao.save(this.mockProfile);
        jpaProfileDao.deleteById(toDeleteId);
        assertThat(jpaProfileDao.findById(toDeleteId)).isEmpty();
    }

    @After
    public void clear() {
        jpaProfileDao.deleteById(this.currentProfileId);
    }

}
