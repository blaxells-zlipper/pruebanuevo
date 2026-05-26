package com.tecsup.petclinic.services;

import com.tecsup.petclinic.dtos.TypeDTO;
import com.tecsup.petclinic.exceptions.TypeNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class TypeServiceTest {

    @Autowired
    private TypeService typeService;

    @Test
    public void testCreateType() {
        TypeDTO typeDTO = TypeDTO.builder()
                .name("ferret")
                .description("Domestic ferret")
                .active(true)
                .sizeCategory("small")
                .averageLifespan(8)
                .careLevel("medium")
                .build();

        TypeDTO createdType = typeService.create(typeDTO);

        assertNotNull(createdType.getId());
        assertEquals("ferret", createdType.getName());
        assertEquals("Domestic ferret", createdType.getDescription());
    }

    @Test
    public void testUpdateType() {
        TypeDTO typeDTO = TypeDTO.builder()
                .name("iguana")
                .description("Large lizard")
                .active(true)
                .sizeCategory("medium")
                .averageLifespan(12)
                .careLevel("high")
                .build();

        TypeDTO createdType = typeService.create(typeDTO);

        createdType.setName("iguana-updated");
        createdType.setDescription("Updated description");
        createdType.setAverageLifespan(14);

        TypeDTO updatedType = typeService.update(createdType);

        assertEquals(createdType.getId(), updatedType.getId());
        assertEquals("iguana-updated", updatedType.getName());
        assertEquals("Updated description", updatedType.getDescription());
        assertEquals(14, updatedType.getAverageLifespan());
    }

    @Test
    public void testFindTypeById() {
        Integer existingId = 1;
        TypeDTO typeDTO = null;

        try {
            typeDTO = typeService.findById(existingId);
        } catch (TypeNotFoundException e) {
            fail(e.getMessage());
        }

        assertNotNull(typeDTO);
        assertEquals(existingId, typeDTO.getId());
        assertEquals("cat", typeDTO.getName());
    }
}
