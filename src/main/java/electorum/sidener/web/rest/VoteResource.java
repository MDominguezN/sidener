package electorum.sidener.web.rest;

import com.codahale.metrics.annotation.Timed;
import electorum.sidener.service.VoteService;
import electorum.sidener.web.rest.util.HeaderUtil;
import electorum.sidener.web.rest.util.PaginationUtil;
import electorum.sidener.service.dto.VoteDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing Vote.
 */
@RestController
@RequestMapping("/api")
public class VoteResource {

    private final Logger log = LoggerFactory.getLogger(VoteResource.class);

    private static final String ENTITY_NAME = "vote";

    private final VoteService voteService;

    public VoteResource(VoteService voteService) {
        this.voteService = voteService;
    }

    /**
     * POST  /votes : Create a new vote.
     *
     * @param voteDTO the voteDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new voteDTO, or with status 400 (Bad Request) if the vote has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/votes")
    @Timed
    public ResponseEntity<VoteDTO> createVote(@RequestBody VoteDTO voteDTO) throws URISyntaxException {
        log.debug("REST request to save Vote : {}", voteDTO);
        if (voteDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new vote cannot already have an ID")).body(null);
        }
        VoteDTO result = voteService.save(voteDTO);
        return ResponseEntity.created(new URI("/api/votes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /votes : Updates an existing vote.
     *
     * @param voteDTO the voteDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated voteDTO,
     * or with status 400 (Bad Request) if the voteDTO is not valid,
     * or with status 500 (Internal Server Error) if the voteDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/votes")
    @Timed
    public ResponseEntity<VoteDTO> updateVote(@RequestBody VoteDTO voteDTO) throws URISyntaxException {
        log.debug("REST request to update Vote : {}", voteDTO);
        if (voteDTO.getId() == null) {
            return createVote(voteDTO);
        }
        VoteDTO result = voteService.save(voteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, voteDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /votes : get all the votes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of votes in body
     */
    @GetMapping("/votes")
    @Timed
    public ResponseEntity<List<VoteDTO>> getAllVotes(@ApiParam Pageable pageable) {
        log.debug("REST request to get a page of Votes");
        Page<VoteDTO> page = voteService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/votes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /votes/:id : get the "id" vote.
     *
     * @param id the id of the voteDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the voteDTO, or with status 404 (Not Found)
     */
    @GetMapping("/votes/{id}")
    @Timed
    public ResponseEntity<VoteDTO> getVote(@PathVariable Long id) {
        log.debug("REST request to get Vote : {}", id);
        VoteDTO voteDTO = voteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(voteDTO));
    }

    /**
     * DELETE  /votes/:id : delete the "id" vote.
     *
     * @param id the id of the voteDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/votes/{id}")
    @Timed
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        log.debug("REST request to delete Vote : {}", id);
        voteService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/votes?query=:query : search for the vote corresponding
     * to the query.
     *
     * @param query the query of the vote search
     * @param pageable the pagination information
     * @return the result of the search
     */
    @GetMapping("/_search/votes")
    @Timed
    public ResponseEntity<List<VoteDTO>> searchVotes(@RequestParam String query, @ApiParam Pageable pageable) {
        log.debug("REST request to search for a page of Votes for query {}", query);
        Page<VoteDTO> page = voteService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/votes");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

}
