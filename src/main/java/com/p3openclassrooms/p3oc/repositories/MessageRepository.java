package com.p3openclassrooms.p3oc.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import com.p3openclassrooms.p3oc.models.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {

}
