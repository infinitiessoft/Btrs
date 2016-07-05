package service.impl;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.api.Invocation;
import org.jmock.lib.action.CustomAction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;

import resources.specification.JobTitleSpecification;
import resources.specification.SimplePageRequest;
import sendto.JobTitleSendto;
import dao.JobTitleDao;
import entity.JobTitle;

public class JobTitleServiceImplTest extends ServiceTest {

	private JobTitleServiceImpl service;
	private JobTitleDao jobTitleDao;
	private JobTitle jobTitle;
	private Specification<JobTitle> spec;

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws Exception {
		jobTitleDao = context.mock(JobTitleDao.class);
		service = new JobTitleServiceImpl(jobTitleDao);
		jobTitle = new JobTitle();
		jobTitle.setId(1L);
		jobTitle.setComment("comment");
		jobTitle.setName("name");

		spec = context.mock(Specification.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieve() {
		context.checking(new Expectations() {

			{
				exactly(1).of(jobTitleDao).findOne(spec);
				will(returnValue(jobTitle));
			}
		});
		JobTitleSendto ret = service.retrieve(spec);
		assertEquals(jobTitle.getId(), ret.getId());
	}

	@Test
	public void testDelete() {
		context.checking(new Expectations() {

			{
				exactly(1).of(jobTitleDao).findOne(spec);
				will(returnValue(jobTitle));

				exactly(1).of(jobTitleDao).delete(jobTitle);
			}
		});
		service.delete(spec);
	}

	@Test
	public void testSave() {
		context.checking(new Expectations() {

			{
				exactly(1).of(jobTitleDao).save(with(any(JobTitle.class)));
				will(new CustomAction("dave jobTitle") {

					@Override
					public Object invoke(Invocation invocation)
							throws Throwable {
						return invocation.getParameter(0);
					}
				});
			}
		});
		JobTitleSendto sendTo = new JobTitleSendto();
		sendTo.setComment("comment");
		sendTo.setName("name");
		JobTitleSendto ret = service.save(sendTo);
		assertEquals(sendTo.getComment(), ret.getComment());
		assertEquals(sendTo.getName(), ret.getName());
	}

	@Test
	public void testFindAll() {
		final JobTitleSpecification spec = new JobTitleSpecification();
		final SimplePageRequest pageable = new SimplePageRequest(0, 20, "id",
				"ASC");
		final List<JobTitle> jobTitles = new ArrayList<JobTitle>();
		jobTitles.add(jobTitle);
		final Page<JobTitle> page = new PageImpl<JobTitle>(jobTitles);
		context.checking(new Expectations() {

			{
				exactly(1).of(jobTitleDao).findAll(spec, pageable);
				will(returnValue(page));
			}
		});
		Page<JobTitleSendto> rets = service.findAll(spec, pageable);
		assertEquals(1, rets.getTotalElements());
		JobTitleSendto ret = rets.iterator().next();
		assertEquals(jobTitle.getId(), ret.getId());
	}

	@Test
	public void testUpdate() {
		context.checking(new Expectations() {

			{
				exactly(1).of(jobTitleDao).findOne(spec);
				will(returnValue(jobTitle));

				exactly(1).of(jobTitleDao).save(jobTitle);
				will(returnValue(jobTitle));
			}
		});
		JobTitleSendto sendTo = new JobTitleSendto();
		sendTo.setComment("comment");
		sendTo.setName("name");
		JobTitleSendto ret = service.update(spec, sendTo);
		assertEquals(jobTitle.getId(), ret.getId());
		assertEquals(sendTo.getComment(), ret.getComment());
		assertEquals(sendTo.getName(), ret.getName());
	}
}
