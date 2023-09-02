package com.example.todolist.util;

import com.example.todolist.dto.response.GenericResponses;
import org.springframework.stereotype.Service;

/**
 * The Interface PageableTools.
 */
@Service
public interface PageableTools {

  /**
   * Gets the pageable list.
   *
   * @param repositoryValueObject the repository value object
   * @return the pageable list
   */
  GenericResponses<?> getPageableList(Object repositoryValueObject);
}
