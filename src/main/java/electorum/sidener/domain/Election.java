package electorum.sidener.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import electorum.sidener.domain.enumeration.Status;

import electorum.sidener.domain.enumeration.RecountDistrictsRule;

import electorum.sidener.domain.enumeration.RecountPollingPlaceRule;

/**
 * Entidad propia de una elección, la cual almacena datos informativos de la elección
 */
@ApiModel(description = "Entidad propia de una elección, la cual almacena datos informativos de la elección")
@Entity
@Table(name = "election")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "election")
public class Election implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Campos Informativos de la Elección
     */
    @ApiModelProperty(value = "Campos Informativos de la Elección")
    @Column(name = "location")
    private String location;

    @Column(name = "jhi_date")
    private ZonedDateTime date;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "prep_url")
    private String prepUrl;

    @Column(name = "ballot_url")
    private String ballotUrl;

    @Column(name = "inset_url")
    private String insetUrl;

    @Column(name = "demand_template_url")
    private String demandTemplateUrl;

    @Column(name = "recount_template_url")
    private String recountTemplateUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "recount_districts_rule")
    private RecountDistrictsRule recountDistrictsRule;

    @Enumerated(EnumType.STRING)
    @Column(name = "recount_polling_place_rule")
    private RecountPollingPlaceRule recountPollingPlaceRule;

    /**
     * Datos Sistema
     */
    @ApiModelProperty(value = "Datos Sistema")
    @Column(name = "published")
    private Boolean published;

    @Column(name = "created")
    private ZonedDateTime created;

    @Column(name = "updated")
    private ZonedDateTime updated;

    /**
     * OK
     */
    @ApiModelProperty(value = "OK")
    @ManyToOne
    private State state;

    /**
     * OK
     */
    @ApiModelProperty(value = "OK")
    @ManyToOne
    private ElectionType electionType;

    /**
     * OK
     */
    @ApiModelProperty(value = "OK")
    @ManyToOne
    private ElectionPeriod electionPeriod;

    /**
     * OK
     */
    @ApiModelProperty(value = "OK")
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "election_political_parties",
               joinColumns = @JoinColumn(name="elections_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="political_parties_id", referencedColumnName="id"))
    private Set<PoliticalParty> politicalParties = new HashSet<>();

    /**
     * OK
     */
    @ApiModelProperty(value = "OK")
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "election_independent_candidates",
               joinColumns = @JoinColumn(name="elections_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="independent_candidates_id", referencedColumnName="id"))
    private Set<IndependentCandidate> independentCandidates = new HashSet<>();

    /**
     * OK
     */
    @ApiModelProperty(value = "OK")
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "election_coalitions",
               joinColumns = @JoinColumn(name="elections_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="coalitions_id", referencedColumnName="id"))
    private Set<Coalition> coalitions = new HashSet<>();

    /**
     * OK
     */
    @ApiModelProperty(value = "OK")
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "election_causals",
               joinColumns = @JoinColumn(name="elections_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="causals_id", referencedColumnName="id"))
    private Set<Causal> causals = new HashSet<>();

    // jhipster-needle-entity-add-field - Jhipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public Election location(String location) {
        this.location = location;
        return this;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Election date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public Election status(Status status) {
        this.status = status;
        return this;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPrepUrl() {
        return prepUrl;
    }

    public Election prepUrl(String prepUrl) {
        this.prepUrl = prepUrl;
        return this;
    }

    public void setPrepUrl(String prepUrl) {
        this.prepUrl = prepUrl;
    }

    public String getBallotUrl() {
        return ballotUrl;
    }

    public Election ballotUrl(String ballotUrl) {
        this.ballotUrl = ballotUrl;
        return this;
    }

    public void setBallotUrl(String ballotUrl) {
        this.ballotUrl = ballotUrl;
    }

    public String getInsetUrl() {
        return insetUrl;
    }

    public Election insetUrl(String insetUrl) {
        this.insetUrl = insetUrl;
        return this;
    }

    public void setInsetUrl(String insetUrl) {
        this.insetUrl = insetUrl;
    }

    public String getDemandTemplateUrl() {
        return demandTemplateUrl;
    }

    public Election demandTemplateUrl(String demandTemplateUrl) {
        this.demandTemplateUrl = demandTemplateUrl;
        return this;
    }

    public void setDemandTemplateUrl(String demandTemplateUrl) {
        this.demandTemplateUrl = demandTemplateUrl;
    }

    public String getRecountTemplateUrl() {
        return recountTemplateUrl;
    }

    public Election recountTemplateUrl(String recountTemplateUrl) {
        this.recountTemplateUrl = recountTemplateUrl;
        return this;
    }

    public void setRecountTemplateUrl(String recountTemplateUrl) {
        this.recountTemplateUrl = recountTemplateUrl;
    }

    public RecountDistrictsRule getRecountDistrictsRule() {
        return recountDistrictsRule;
    }

    public Election recountDistrictsRule(RecountDistrictsRule recountDistrictsRule) {
        this.recountDistrictsRule = recountDistrictsRule;
        return this;
    }

    public void setRecountDistrictsRule(RecountDistrictsRule recountDistrictsRule) {
        this.recountDistrictsRule = recountDistrictsRule;
    }

    public RecountPollingPlaceRule getRecountPollingPlaceRule() {
        return recountPollingPlaceRule;
    }

    public Election recountPollingPlaceRule(RecountPollingPlaceRule recountPollingPlaceRule) {
        this.recountPollingPlaceRule = recountPollingPlaceRule;
        return this;
    }

    public void setRecountPollingPlaceRule(RecountPollingPlaceRule recountPollingPlaceRule) {
        this.recountPollingPlaceRule = recountPollingPlaceRule;
    }

    public Boolean isPublished() {
        return published;
    }

    public Election published(Boolean published) {
        this.published = published;
        return this;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public Election created(ZonedDateTime created) {
        this.created = created;
        return this;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public Election updated(ZonedDateTime updated) {
        this.updated = updated;
        return this;
    }

    public void setUpdated(ZonedDateTime updated) {
        this.updated = updated;
    }

    public State getState() {
        return state;
    }

    public Election state(State state) {
        this.state = state;
        return this;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ElectionType getElectionType() {
        return electionType;
    }

    public Election electionType(ElectionType electionType) {
        this.electionType = electionType;
        return this;
    }

    public void setElectionType(ElectionType electionType) {
        this.electionType = electionType;
    }

    public ElectionPeriod getElectionPeriod() {
        return electionPeriod;
    }

    public Election electionPeriod(ElectionPeriod electionPeriod) {
        this.electionPeriod = electionPeriod;
        return this;
    }

    public void setElectionPeriod(ElectionPeriod electionPeriod) {
        this.electionPeriod = electionPeriod;
    }

    public Set<PoliticalParty> getPoliticalParties() {
        return politicalParties;
    }

    public Election politicalParties(Set<PoliticalParty> politicalParties) {
        this.politicalParties = politicalParties;
        return this;
    }

    public Election addPoliticalParties(PoliticalParty politicalParty) {
        this.politicalParties.add(politicalParty);
        return this;
    }

    public Election removePoliticalParties(PoliticalParty politicalParty) {
        this.politicalParties.remove(politicalParty);
        return this;
    }

    public void setPoliticalParties(Set<PoliticalParty> politicalParties) {
        this.politicalParties = politicalParties;
    }

    public Set<IndependentCandidate> getIndependentCandidates() {
        return independentCandidates;
    }

    public Election independentCandidates(Set<IndependentCandidate> independentCandidates) {
        this.independentCandidates = independentCandidates;
        return this;
    }

    public Election addIndependentCandidates(IndependentCandidate independentCandidate) {
        this.independentCandidates.add(independentCandidate);
        return this;
    }

    public Election removeIndependentCandidates(IndependentCandidate independentCandidate) {
        this.independentCandidates.remove(independentCandidate);
        return this;
    }

    public void setIndependentCandidates(Set<IndependentCandidate> independentCandidates) {
        this.independentCandidates = independentCandidates;
    }

    public Set<Coalition> getCoalitions() {
        return coalitions;
    }

    public Election coalitions(Set<Coalition> coalitions) {
        this.coalitions = coalitions;
        return this;
    }

    public Election addCoalitions(Coalition coalition) {
        this.coalitions.add(coalition);
        return this;
    }

    public Election removeCoalitions(Coalition coalition) {
        this.coalitions.remove(coalition);
        return this;
    }

    public void setCoalitions(Set<Coalition> coalitions) {
        this.coalitions = coalitions;
    }

    public Set<Causal> getCausals() {
        return causals;
    }

    public Election causals(Set<Causal> causals) {
        this.causals = causals;
        return this;
    }

    public Election addCausals(Causal causal) {
        this.causals.add(causal);
        return this;
    }

    public Election removeCausals(Causal causal) {
        this.causals.remove(causal);
        return this;
    }

    public void setCausals(Set<Causal> causals) {
        this.causals = causals;
    }
    // jhipster-needle-entity-add-getters-setters - Jhipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Election election = (Election) o;
        if (election.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), election.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Election{" +
            "id=" + getId() +
            ", location='" + getLocation() + "'" +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", prepUrl='" + getPrepUrl() + "'" +
            ", ballotUrl='" + getBallotUrl() + "'" +
            ", insetUrl='" + getInsetUrl() + "'" +
            ", demandTemplateUrl='" + getDemandTemplateUrl() + "'" +
            ", recountTemplateUrl='" + getRecountTemplateUrl() + "'" +
            ", recountDistrictsRule='" + getRecountDistrictsRule() + "'" +
            ", recountPollingPlaceRule='" + getRecountPollingPlaceRule() + "'" +
            ", published='" + isPublished() + "'" +
            ", created='" + getCreated() + "'" +
            ", updated='" + getUpdated() + "'" +
            "}";
    }
}